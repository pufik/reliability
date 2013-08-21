package ua.edu.lp.reliability.facade.converter;

import java.util.Collection;
import java.util.List;

public interface Converter<SOURCE, TARGET> {
	
	TARGET convert(SOURCE source);

	TARGET convert(SOURCE source, TARGET target);
	
	List<TARGET> convertAll(Collection<SOURCE> sources);
	
	void convertAll(Collection<SOURCE> sources, Collection<TARGET> targets);
}
