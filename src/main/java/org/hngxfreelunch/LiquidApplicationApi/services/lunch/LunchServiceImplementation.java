package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private LunchRepository lunchRepository;

}
