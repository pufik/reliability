package ua.edu.lp.reliability.facade.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractConverter<SOURCE, TARGET> implements Converter<SOURCE, TARGET> {

	@Override
	public List<TARGET> convertAll(Collection<SOURCE> sources) {
		List<TARGET> resultList = new ArrayList<TARGET>(sources.size());

		for (SOURCE source : sources) {
			resultList.add(convert(source));
		}

		return resultList;
	}

	@Override
	public void convertAll(Collection<SOURCE> sources, Collection<TARGET> targets) {
		for (SOURCE source : sources) {
			targets.add(convert(source));
		}
	}
}
