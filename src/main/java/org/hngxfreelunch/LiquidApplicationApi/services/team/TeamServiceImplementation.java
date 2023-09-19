package org.hngxfreelunch.LiquidApplicationApi.services.team;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.TeamRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImplementation implements TeamService {

    private TeamRepository teamRepository;

}
