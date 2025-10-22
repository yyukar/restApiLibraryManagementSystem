package com.example.Library.Management.System.service.impl;

import com.example.Library.Management.System.core.exceptions.NotFoundException;
import com.example.Library.Management.System.domain.entity.Publisher;
import com.example.Library.Management.System.repository.PublisherRepository;
import com.example.Library.Management.System.service.interfaces.PublisherService;
import com.example.Library.Management.System.web.dto.publisher.PublisherRequest;
import com.example.Library.Management.System.web.dto.publisher.PublisherResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ModelMapper mapper;

    @Override
    public PublisherResponse create(PublisherRequest req) {
        Publisher p = mapper.map(req, Publisher.class);
        return mapper.map(publisherRepository.save(p), PublisherResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PublisherResponse get(Long id) {
        Publisher p = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: " + id));
        return mapper.map(p, PublisherResponse.class); // address DTO'da yok
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublisherResponse> list() {
        return publisherRepository.findAll()
                .stream().map(p -> mapper.map(p, PublisherResponse.class)).toList();
    }

    @Override
    public PublisherResponse update(Long id, PublisherRequest req) {
        Publisher p = publisherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Yayınevi bulunamadı: " + id));
        p.setName(req.getName());
        p.setEstablishmentYear(req.getEstablishmentYear());
        p.setAddress(req.getAddress());
        return mapper.map(p, PublisherResponse.class); // address yine dönülmüyor
    }

    @Override
    public void delete(Long id) {
        if (!publisherRepository.existsById(id))
            throw new NotFoundException("Yayınevi bulunamadı: " + id);
        publisherRepository.deleteById(id);
    }
}

