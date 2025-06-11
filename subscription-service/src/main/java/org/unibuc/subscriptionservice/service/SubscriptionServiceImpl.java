package org.unibuc.subscriptionservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.unibuc.subscriptionservice.client.StudioClient;
import org.unibuc.subscriptionservice.dto.StudioDTO;
import org.unibuc.subscriptionservice.dto.SubscriptionCreateRequestDTO;
import org.unibuc.subscriptionservice.dto.SubscriptionDTO;
import org.unibuc.subscriptionservice.entities.Subscription;
import org.unibuc.subscriptionservice.mapper.SubscriptionMapper;
import org.unibuc.subscriptionservice.repository.SubscriptionRepository;
import org.unibuc.subscriptionservice.security.JwtUtil;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final StudioClient studioClient;
    private final JwtUtil jwtUtil;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   SubscriptionMapper subscriptionMapper,
                                   StudioClient studioClient,
                                   JwtUtil jwtUtil) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.studioClient = studioClient;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public SubscriptionDTO createSubscription(SubscriptionCreateRequestDTO request, String token) {
        String email = jwtUtil.extractUsername(token);

        //  Get studio by name
        StudioDTO studio = studioClient.getByName(request.getStudioName());
        if (studio == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio not found");
        }
        Subscription subscription = subscriptionMapper.toEntity(request, email, studio.getId());
        Subscription saved = subscriptionRepository.save(subscription);

        return subscriptionMapper.toDto(saved);
    }

    private Long getUserIdFromUsername(String username) {

        throw new UnsupportedOperationException("getUserIdFromUsername needs implementation.");
    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsForUser(String token) {
        String email = jwtUtil.extractUsername(token);

        List<Subscription> subs = subscriptionRepository.findAllByUserEmail(email);
        return subs.stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }
}
