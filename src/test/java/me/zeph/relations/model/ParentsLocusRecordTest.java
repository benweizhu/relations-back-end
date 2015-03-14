package me.zeph.relations.model;

import com.google.common.collect.Lists;
import me.zeph.relations.model.api.ParentsLocusRecord;
import me.zeph.relations.model.api.Unit;
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
				//14p,15q,16r
				{15d, 15d, 16d, 15d, 15d, 15d, "qq.rq.qq"},
				{15d, 15d, 15d, 16d, 15d, 15d, "qq.qr.qq"},
				{14d, 15d, 14d, 14d, 15d, 15d, "pq.pp.qq"},
				{14d, 15d, 15d, 15d, 14d, 14d, "pq.qq.pp"},
				{14d, 15d, 14d, 16d, 15d, 15d, "pq.pr.qq"},
				{14d, 15d, 16d, 14d, 15d, 15d, "pq.rp.qq"},
				{14d, 15d, 15d, 16d, 14d, 14d, "pq.qr.pp"},
				{14d, 15d, 16d, 15d, 14d, 14d, "pq.rq.pp"},
				{15d, 15d, 15d, 15d, 15d, 15d, "qq.qq.qq"},
				{14d, 15d, 14d, 14d, 15d, 16d, "pq.pp.qr"},
				{14d, 15d, 14d, 14d, 16d, 15d, "pq.pp.rq"},
				{14d, 15d, 15d, 15d, 14d, 16d, "pq.qq.pr"},
				{14d, 15d, 15d, 15d, 16d, 14d, "pq.qq.rp"},
				{14d, 15d, 14d, 14d, 14d, 15d, "pq.pp.pq"},
				{14d, 15d, 14d, 14d, 15d, 14d, "pq.pp.qp"},
				{14d, 15d, 15d, 15d, 14d, 15d, "pq.qq.pq"},
				{14d, 15d, 15d, 15d, 15d, 14d, "pq.qq.qp"},
				{14d, 15d, 14d, 16d, 15d, 16d, "pq.pr.qr"},
				{14d, 15d, 14d, 16d, 16d, 15d, "pq.pr.rq"},
				{14d, 15d, 16d, 14d, 15d, 16d, "pq.rp.qr"},
				{14d, 15d, 16d, 14d, 16d, 15d, "pq.rp.rq"},
				{14d, 15d, 15d, 16d, 14d, 16d, "pq.qr.pr"},
				{14d, 15d, 15d, 16d, 16d, 14d, "pq.qr.rp"},
				{14d, 15d, 16d, 15d, 14d, 16d, "pq.rq.pr"},
				{14d, 15d, 16d, 15d, 16d, 14d, "pq.rq.rp"},
				{14d, 15d, 14d, 16d, 14d, 15d, "pq.pr.pq"},
				{14d, 15d, 14d, 16d, 15d, 14d, "pq.pr.qp"},
				{14d, 15d, 16d, 14d, 14d, 15d, "pq.rp.pq"},
				{14d, 15d, 16d, 14d, 15d, 14d, "pq.rp.qp"},
				{14d, 15d, 15d, 16d, 14d, 15d, "pq.qr.pq"},
				{14d, 15d, 15d, 16d, 15d, 14d, "pq.qr.qp"},
				{14d, 15d, 16d, 15d, 14d, 15d, "pq.rq.pq"},
				{14d, 15d, 16d, 15d, 15d, 14d, "pq.rq.qp"},
				{15d, 15d, 16d, 15d, 15d, 16d, "qq.rq.qr"},
				{15d, 15d, 16d, 15d, 16d, 15d, "qq.rq.rq"},
				{15d, 15d, 15d, 16d, 15d, 16d, "qq.qr.qr"},
				{15d, 15d, 15d, 16d, 16d, 15d, "qq.qr.rq"},
				{15d, 15d, 15d, 15d, 15d, 16d, "qq.qq.qr"},
				{15d, 15d, 15d, 15d, 16d, 15d, "qq.qq.rq"},
				{14d, 15d, 14d, 15d, 14d, 15d, "pq.pq.pq"},
				{14d, 15d, 14d, 15d, 15d, 14d, "pq.pq.qp"},
				{14d, 15d, 15d, 14d, 14d, 15d, "pq.qp.pq"},
				{14d, 15d, 15d, 14d, 15d, 14d, "pq.qp.qp"},
				{14d, 15d, 14d, 15d, 15d, 15d, "pq.pq.qq"},
				{14d, 15d, 15d, 14d, 15d, 15d, "pq.qp.qq"},
				{14d, 15d, 14d, 15d, 14d, 14d, "pq.pq.pp"},
				{14d, 15d, 15d, 14d, 14d, 14d, "pq.qp.pp"},
				{14d, 15d, 14d, 15d, 15d, 16d, "pq.pq.qr"},
				{14d, 15d, 14d, 15d, 16d, 15d, "pq.pq.rq"},
				{14d, 15d, 15d, 14d, 15d, 16d, "pq.qp.qr"},
				{14d, 15d, 15d, 14d, 16d, 15d, "pq.qp.rq"},
				{14d, 15d, 14d, 15d, 14d, 16d, "pq.pq.pr"},
				{14d, 15d, 14d, 15d, 16d, 14d, "pq.pq.rp"},
				{14d, 15d, 15d, 14d, 14d, 16d, "pq.qp.pr"},
				{14d, 15d, 15d, 14d, 16d, 14d, "pq.qp.rp"},
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