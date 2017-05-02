package com.dreamer.domain.system;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Comparator;
@Component
public class ModuleComparator implements Comparator<Module>,Serializable {
	@Override
	public int compare(Module o1, Module o2) {
		// TODO Auto-generated method stub
		return o1.getSequence().compareTo(o2.getSequence());
	}

}
