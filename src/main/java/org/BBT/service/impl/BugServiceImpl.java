package org.BBT.service.impl;

import org.BBT.data.entity.BugEntity;
import org.BBT.data.repository.BugRepository;
import org.BBT.service.BugService;
import org.BBT.service.dto.BugDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private ModelMapper modelMapper;

    private BugEntity convertToEntity(BugDto dto) {
        return modelMapper.map(dto, BugEntity.class);
    }

    private BugDto convertToDto(BugEntity entity) {
        return modelMapper.map(entity, BugDto.class);
    }

    @Override
    @Transactional
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
            modelMapper.map(bugDto, bugEntity); // Frissítés ModelMapper-rel
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
        return bugEntityOptional.map(this::convertToDto).orElse(null);
    }
}
