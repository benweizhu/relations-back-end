package me.zeph.relations.model;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ParentsLocusRecordTest {
	private double c1;
	private double c2;
	private double m1;
	private double m2;
	private double af1;
	private double af2;
	private String pattern;

	public ParentsLocusRecordTest(double c1, double c2, double m1, double m2, double af1, double af2, String pattern) {
		this.c1 = c1;
		this.c2 = c2;
		this.m1 = m1;
		this.m2 = m2;
		this.af1 = af1;
		this.af2 = af2;
		this.pattern = pattern;
	}

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Lists.newArrayList(new Object[][]{
				{15d, 15d, 15d, 15d, 15d, 15d, "qq.qq.qq"},
				{14d, 15d, 14d, 14d, 15d, 15d, "pq.pp.qq"},
		});
	}

	@Test
	public void shouldReturnCorrectPattern() {
		Unit c1Unit = new Unit(c1, 0);
		Unit c2Unit = new Unit(c2, 0);
		Unit m1Unit = new Unit(m1, 0);
		Unit m2Unit = new Unit(m2, 0);
		Unit af1Unit = new Unit(af1, 0);
		Unit afUnit = new Unit(af2, 0);

		ParentsLocusRecord parentsLocusRecord = new ParentsLocusRecord(c1Unit, c2Unit, m1Unit, m2Unit, af1Unit, afUnit);

		assertThat(parentsLocusRecord.getPattern(), is(pattern));
	}
}