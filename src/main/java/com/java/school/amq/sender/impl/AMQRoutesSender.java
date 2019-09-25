package com.java.school.amq.sender.impl;

import com.java.school.amq.sender.AMQSender;
import com.java.school.domain.City;
import com.java.school.domain.CityRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AMQRoutesSender extends AMQSender {

    // Debe ser mayor a 2
    private static final int ROUTES_QTY = 5;
    private static final int MAX_DISTANCE = 100;

    private String cityOrigin;
    private String cityDestination;

    @Override
    public Object getFromRepo() {
        Set<CityRoute> routes = new HashSet<>();
        List<String> citiesToExclude = Arrays.asList(cityOrigin.toLowerCase(), cityDestination.toLowerCase());
        List<City> allCities = applicationRepository.getCities();

        List<String> cityCandidates = allCities.stream()
                .map(City::getName)
                .filter(city -> !citiesToExclude.contains(city.toLowerCase()))
                .collect(Collectors.toList());

        List<CityRoute> firstLevel = Collections.singletonList(CityRoute.builder().to(cityOrigin).build());
        List<CityRoute> secondLevel = generateCityBranchLevel(cityCandidates, firstLevel, ROUTES_QTY, ROUTES_QTY);
        List<CityRoute> thirdLevel = generateCityBranchLevel(cityCandidates, secondLevel, 2, ROUTES_QTY);
        List<CityRoute> forthLevel = generateCityBranchLevel(cityCandidates, thirdLevel, 1, 3);
        List<CityRoute> fifthLevel = generateCityBranchLevel(Collections.singletonList(cityDestination), forthLevel, 1, 1);

        routes.addAll(secondLevel);
        routes.addAll(thirdLevel);
        routes.addAll(forthLevel);
        routes.addAll(fifthLevel);

        return routes;
    }

    private List<CityRoute> generateCityBranchLevel(List<String> cityCandidates,
                                                    List<CityRoute> currentLevel,
                                                    int minNumberOfNodes,
                                                    int maxNumberOfNodes) {
        List<CityRoute> newBranchLevel = new ArrayList<>();

        for (CityRoute currentLevelCityRoute : currentLevel) {
            List<String> randomCitySubset = cityCandidates;

            if (Objects.nonNull(cityCandidates) && cityCandidates.size() > 1) {
                int toIndex = minNumberOfNodes < maxNumberOfNodes
                        ? ThreadLocalRandom.current().nextInt(minNumberOfNodes, maxNumberOfNodes)
                        : maxNumberOfNodes;

                Collections.shuffle(cityCandidates);
                randomCitySubset = cityCandidates.subList(0, toIndex);
            }

            newBranchLevel.addAll(randomCitySubset.stream()
                    .filter(city -> !city.equalsIgnoreCase(currentLevelCityRoute.getTo()))
                    .map(city -> CityRoute.builder()
                            .from(currentLevelCityRoute.getTo())
                            .to(city)
                            .distance(getDistance())
                            .build())
                    .collect(Collectors.toList()));
        }

        return newBranchLevel;
    }

    private String getDistance() {
        return ThreadLocalRandom.current().nextInt(1, MAX_DISTANCE + 1) + "";
    }

    public void setCityOrigin(String cityOrigin) {
        this.cityOrigin = cityOrigin;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }
}
