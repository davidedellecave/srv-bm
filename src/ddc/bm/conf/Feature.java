package ddc.bm.conf;

import ddc.support.util.LRange;

public class Feature {
	private LRange period = LRange.EMPTY;
	
	public LRange getPeriod() {
		return period;
	}

	public void setPeriod(LRange period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return period.toString();
	}
}