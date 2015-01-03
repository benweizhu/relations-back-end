package me.zeph.relations.pattern;

import com.google.common.collect.Lists;
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
				{14d, 14d, 14d, 14d, "QQ QQ"},
				{14d, 15d, 15d, 15d, "PQ QQ"},
				{14d, 15d, 14d, 14d, "PQ PP"},
				{15d, 15d, 15d, 16d, "QQ QR"},
				{15d, 15d, 14d, 15d, "QQ RQ"},
				{14d, 15d, 14d, 15d, "PQ PQ"},
				{14d, 15d, 15d, 14d, "PQ QP"},
				{14d, 15d, 15d, 16d, "PQ QR"},
				{14d, 15d, 16d, 15d, "PQ RQ"},
				{14d, 15d, 14d, 16d, "PQ PR"},
				{14d, 15d, 16d, 14d, "PQ RP"}
		});

	}

	@Test
	public void shouldReturnCorrectPatten() {
		OneParentLocusRecord oneParentLocusRecord = new OneParentLocusRecord(c1, c2, af1, af2);
		assertThat(oneParentLocusRecord.getPattern(), is(pattern));
	}
}