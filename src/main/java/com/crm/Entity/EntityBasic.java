package com.crm.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface EntityBasic extends Serializable {
	public UUID getId();
	
	public void setId(UUID id);
	
	public boolean isDeleted();
	
	public void setDeleted(boolean deleted);
	
	public LocalDateTime getCreatedDate();
	
	public void setCreatedDate(LocalDateTime createDate);
	
	public LocalDateTime getUpdatedDate();
	
	public void setUpdatedDate(LocalDateTime updatedDate);
}
