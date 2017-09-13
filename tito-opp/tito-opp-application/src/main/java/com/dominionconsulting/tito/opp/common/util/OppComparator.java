package com.dominionconsulting.tito.opp.common.util;

import java.util.Comparator;

import com.dominionconsulting.tito.opp.dto.OppHomeDto;

public class OppComparator implements Comparator<OppHomeDto> {

	@Override
	public int compare(OppHomeDto arg0, OppHomeDto arg1) {
		int accountNameCompare = 0;
		String account0 = arg0.getAccountName();
		String account1 = arg1.getAccountName();
		if (account0 == null && account1 != null) return 1;
		if (account0 != null && account1 == null) return -1;
		if (account0 != null && account1 !=null) {
			accountNameCompare = account0.compareTo(account1);
		}
		if (accountNameCompare != 0) return accountNameCompare;
		String stage0 = arg0.getStage();
		String stage1 = arg1.getStage();
		if (stage0 == null) return 1;
		if (stage1 == null) return -1;
		return stage0.compareTo(stage1);
	}

}
