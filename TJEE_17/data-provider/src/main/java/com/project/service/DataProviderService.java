package com.project.service;

import com.project.model.HashDataRepository;
import com.project.model.dto.HashDataDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DataProviderService {

    @Inject
    private HashDataRepository hashDataRepository;

    public List<HashDataDTO> getHashData() {
        return hashDataRepository.getHashData().stream()
                .map(hashData -> new HashDataDTO(hashData.getHashValue(), hashData.getCreatedDate()))
                .collect(Collectors.toList());
    }

    public void createHashData(String hashValue) {
        hashDataRepository.createHashData(hashValue);
    }

    public void deleteHashData(String hashValue) {
        hashDataRepository.deleteHashData(hashValue);
    }
}
