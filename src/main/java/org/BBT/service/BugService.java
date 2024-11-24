package org.BBT.service;

import org.BBT.data.entity.BugEntity;
import org.BBT.service.dto.BugDto;

import java.util.List;

public interface BugService {
    BugDto createBug(BugDto bugDto);
    BugDto updateBug(BugDto bugDto);
    void deleteBug(BugDto bugDto);
    List<BugDto> getBugs();
    BugDto getBugById(Long id);

    BugEntity convertToEntity(BugDto bug);

    BugDto convertToDto(BugEntity bug);
}
