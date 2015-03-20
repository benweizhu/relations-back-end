package me.zeph.relations.service;

import me.zeph.relations.exception.LocationNotFoundException;
import me.zeph.relations.model.api.Location;
import me.zeph.relations.model.entity.LocationEntity;
import me.zeph.relations.repository.LocationRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class LocationServiceTest {

	private LocationService locationService;
	private LocationRepository locationRepository;

	@Before
	public void setUp() throws Exception {
		locationService = new LocationService();
		locationRepository = mock(LocationRepository.class);
		setField(locationService, "locationRepository", locationRepository);
	}

	@Test
	public void shouldTranslateLocations() {
		when(locationRepository.findAll()).thenReturn(newArrayList(getLocationEntity(1L, "name")));

		List<Location> locations = locationService.getLocations();

		assertThat(locations.size(), is(1));
		assertThat(locations.get(0).getLocationId(), is(1L));
	}

	@Test
	public void shouldTranslateLocationByLocationId() {
		when(locationRepository.findOne(anyLong())).thenReturn(getLocationEntity(1L, "name"));

		Location location = locationService.getLocation(1L);

		assertThat(location.getLocationId(), is(1L));
		assertThat(location.getName(), is("name"));
	}

	@Test(expected = LocationNotFoundException.class)
	public void shouldThrowLocationNotFoundException() {
		when(locationRepository.findOne(anyLong())).thenReturn(null);
		locationService.getLocation(1L);
	}

	private LocationEntity getLocationEntity(long id, String name) {
		LocationEntity locationEntity = new LocationEntity();
		setField(locationEntity, "id", id);
		setField(locationEntity, "name", name);
		return locationEntity;
	}
}