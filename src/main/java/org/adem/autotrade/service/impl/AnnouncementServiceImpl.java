package org.adem.autotrade.service.impl;


import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.dto.request.AnnouncementRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.exception.AnnouncementNotFoundException;
import org.adem.autotrade.exception.CarNotFoundException;
import org.adem.autotrade.mapper.AnnouncementMapper;
import org.adem.autotrade.mapper.CarMapper;
import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.repository.AnnouncementRepository;
import org.adem.autotrade.service.AnnouncementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final CarMapper carMapper;

    @Override
    public void addAnnouncement(AnnouncementRequestDto announcement) {
        Announcement announcement1 = announcementMapper.toAnnouncementEntity(announcement);
        announcementRepository.save(announcement1);
        log.info("Announcement added with id:{}", announcement1.getId());
    }

    @Override
    public void updateAnnouncementByID(Integer id, AnnouncementRequestDto announcement) {
        Optional<Announcement> existingAnnouncement = announcementRepository.findById(id);
        if (existingAnnouncement.isEmpty()) {
            throw new AnnouncementNotFoundException("Announcement not found");
        }
        existingAnnouncement.get().setAnnouncementName(announcement.getAnnouncementName());
        existingAnnouncement.get().setCreateDate(announcement.getCreateDate());
        existingAnnouncement.get().setUpdateDate(announcement.getUpdateDate());
        existingAnnouncement.get().setExpireDate(announcement.getExpireDate());
        existingAnnouncement.get().setViewCount(announcement.getViewCount());
        existingAnnouncement.get().setDescription(announcement.getDescription());
        existingAnnouncement.get().setStatus(announcement.getStatus());

        existingAnnouncement.get().setCar(carMapper.toCarEntity(
                carMapper.fromResponseToRequest(announcement.getCar())));

        announcementRepository.save(existingAnnouncement.get());

        log.info("Announcement updated with id:{}", id);

    }

    @Override
    public Page<AnnouncementResponseDto> getAllAnnouncements(Pageable pageable) {
        return announcementRepository.findAll(pageable)
                .map(announcementMapper::toAnnouncementResponse);
    }

    @Override
    public AnnouncementResponseDto getAnnouncementByID(Integer id) {
        return announcementRepository.findById(id)
                .stream()
                .map(announcementMapper::toAnnouncementResponse)
                .findFirst()
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));
    }

    @Override
    public AnnouncementResponseDto getAnnouncementByCarID(Integer carId) {
        return announcementRepository.findByCar_Id(carId)
                .stream()
                .map(announcementMapper::toAnnouncementResponse)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException("Announcement not found. There is no car with this id"));
    }

    @Override
    public void deleteAnnouncementByID(Integer id) {
        if (announcementRepository.findById(id).isEmpty()) {
            throw new AnnouncementNotFoundException("Announcement not found");
        }
        announcementRepository.deleteById(id);

    }

    @Override
    public Page<AnnouncementResponseDto> findBySpecification(String name, LocalDateTime createDate, Pageable pageable) {
        Specification<Announcement> specification = null;

        specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (name != null) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (createDate != null) {
                predicateList.add(criteriaBuilder.equal(root.get("createDate"), createDate));
            }
            Objects.requireNonNull(query).where(
                    criteriaBuilder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])))
            );

            return query.getRestriction();
        };
        return announcementRepository.findAll(specification, pageable).map(announcementMapper::toAnnouncementResponse);
    }
}
