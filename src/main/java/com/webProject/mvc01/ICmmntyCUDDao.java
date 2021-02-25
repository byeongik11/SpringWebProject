package com.webProject.mvc01;

public interface ICmmntyCUDDao {
	
	int bodNoMax();
	
	CmmntyDto bodNoSltOne(int bod_no);
	
	int cmmntyInsert(CmmntyDto cmmntyDto);
	
	int cmmntyUpdate(CmmntyDto cmmntyDto);
	
	int cmmntyDelete(CmmntyDto cmmntyDto);
	
	
	
}
