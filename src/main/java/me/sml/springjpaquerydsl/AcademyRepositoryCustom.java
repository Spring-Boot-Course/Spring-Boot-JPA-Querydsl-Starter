package me.sml.springjpaquerydsl;

import java.util.List;

public interface AcademyRepositoryCustom {
    List<Academy> findByName(String name);
}
