package com.microsoft.dre.util;

import java.util.Comparator;

public class SortByAgeDiffrence implements Comparator<MatchingUser> {

	@Override
	public int compare(MatchingUser o1, MatchingUser o2) {

		if (o1.getAgeDiffrence() < o2.getAgeDiffrence())
			return -1;
		else if (o1.getAgeDiffrence() > o2.getAgeDiffrence())
			return 1;
		else {
			if (o1.getMatchingIntrestsCount() > o2.getMatchingIntrestsCount())
				return -1;
			else if (o1.getMatchingIntrestsCount() < o2.getMatchingIntrestsCount())
				return 1;
			else
				return 0;
		}
	}

}
