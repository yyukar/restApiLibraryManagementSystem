package com.example.Library.Management.System.service.interfaces;

import com.example.Library.Management.System.web.dto.publisher.PublisherRequest;
import com.example.Library.Management.System.web.dto.publisher.PublisherResponse;

import java.util.List;

public interface PublisherService {
    PublisherResponse create(PublisherRequest req);
    PublisherResponse get(Long id);
    List<PublisherResponse> list();
    PublisherResponse update(Long id, PublisherRequest req);
    void delete(Long id);
}
