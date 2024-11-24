package org.BBT.service.impl;

import org.BBT.data.entity.BugEntity;
import org.BBT.data.repository.BugRepository;
import org.BBT.service.BugService;
import org.BBT.service.dto.BugDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    // BugDto konvertálása BugEntity-be
    public BugEntity convertToEntity(BugDto dto) {
        BugEntity entity = new BugEntity();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        return entity;
    }


    // BugEntity konvertálása BugDto-ra
    public BugDto convertToDto(BugEntity entity) {
        return new BugDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

    @Override
    public BugDto createBug(BugDto bugDto) {
        BugEntity bugEntity = convertToEntity(bugDto);
        bugEntity = bugRepository.save(bugEntity);
        return convertToDto(bugEntity);
    }

    @Override
    public BugDto updateBug(BugDto bugDto) {
        Optional<BugEntity> optionalBug = bugRepository.findById(bugDto.getId());
        if (optionalBug.isPresent()) {
            BugEntity bugEntity = optionalBug.get();
            bugEntity.setTitle(bugDto.getTitle());
            bugEntity.setDescription(bugDto.getDescription());
            bugEntity = bugRepository.save(bugEntity);
            return convertToDto(bugEntity);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBug(BugDto bugDto) {
        bugRepository.deleteById(bugDto.getId());
    }

    @Override
    public List<BugDto> getBugs() {
        List<BugEntity> bugEntities = bugRepository.findAll();
        return bugEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BugDto getBugById(Long id) {
        Optional<BugEntity> bugEntityOptional = bugRepository.findById(id);
        if (bugEntityOptional.isPresent()) {
            BugEntity bugEntity = bugEntityOptional.get();
            return convertToDto(bugEntity);
        } else {
            return null;
        }
    }

}
