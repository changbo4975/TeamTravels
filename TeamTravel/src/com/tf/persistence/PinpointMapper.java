package com.tf.persistence;

import java.util.List;

import com.tf.domain.Pinpoint;

public interface PinpointMapper {
	public List<Pinpoint> list(Pinpoint pinpoint);
	public Pinpoint select(Pinpoint pinpoint);
	public void insert(Pinpoint pinpoint);
	public void update(Pinpoint pinpoint);
	public void delete(Pinpoint pinpoint);
}
