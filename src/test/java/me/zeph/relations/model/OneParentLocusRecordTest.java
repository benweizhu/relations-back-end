package me.zeph.relations.model;

import com.google.common.collect.Lists;
import me.zeph.relations.model.api.OneParentLocusRecord;
import me.zeph.relations.model.api.Unit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class OneParentLocusRecordTest {

	private double c1;
	private double c2;
	private double af1;
	private double af2;
	private String pattern;


	public OneParentLocusRecordTest(double c1, double c2, double af1, double af2, String pattern) {
		this.c1 = c1;
		this.c2 = c2;
		this.af1 = af1;
		this.af2 = af2;
		this.pattern = pattern;
	}

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Lists.newArrayList(new Object[][]{
				{14d, 14d, 14d, 14d, "qq.qq"},
				{14d, 15d, 15d, 15d, "pq.qq"},
				{14d, 15d, 14d, 14d, "pq.pp"},
				{15d, 15d, 15d, 16d, "qq.qr"},
				{15d, 15d, 14d, 15d, "qq.rq"},
				{14d, 15d, 14d, 15d, "pq.pq"},
				{14d, 15d, 15d, 14d, "pq.qp"},
				{14d, 15d, 15d, 16d, "pq.qr"},
				{14d, 15d, 16d, 15d, "pq.rq"},
				{14d, 15d, 14d, 16d, "pq.pr"},
				{14d, 15d, 16d, 14d, "pq.rp"}
		});

	}

	@Test
	public void shouldReturnCorrectPattern() {
		Unit c1Unit = new Unit(c1, 0);
		Unit c2Unit = new Unit(c2, 0);
		Unit af1Unit = new Unit(af1, 0);
		Unit afUnit = new Unit(af2, 0);

		OneParentLocusRecord oneParentLocusRecord = new OneParentLocusRecord(c1Unit, c2Unit, af1Unit, afUnit);

		assertThat(oneParentLocusRecord.getPattern(), is(pattern));
	}
}