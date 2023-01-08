/*
 * Copyright (c) 2023 Zavarov
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package zav.jrc.databind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a trophy list entity have been properly deserialized.
 */
public class TrophyListEntityTest extends AbstractTest {
  static TrophyListEntity trophyList;
  static AwardEntity threeYearClub;
  static AwardEntity verifiedEmail;

  /**
   * Instantiates the trophy list DTO and retrieves the individual awards.
   */
  @BeforeAll
  public static void setUpAll() {
    trophyList = read("TrophyList.json", TrophyListEntity.class);
    threeYearClub = read(trophyList.getTrophies().get(0).getData(), AwardEntity.class);
    verifiedEmail = read(trophyList.getTrophies().get(1).getData(), AwardEntity.class);
  }

  @Test
  public void testGetTrophies() {
    assertEquals(trophyList.getTrophies().size(), 2);
  }

  @Test
  public void testGetThreeYearClubTrophy() {
    assertEquals(threeYearClub.getIcon70(), "https://www.redditstatic.com/awards2/3_year_club-70.png");
    assertEquals(threeYearClub.getGrantedAt(), 1592849930);
    assertNull(threeYearClub.getUrl());
    assertEquals(threeYearClub.getIcon40(), "https://www.redditstatic.com/awards2/3_year_club-40.png");
    assertEquals(threeYearClub.getName(), "Three-Year Club");
    assertNull(threeYearClub.getAwardId());
    assertNull(threeYearClub.getId());
    assertNull(threeYearClub.getDescription());
  }

  @Test
  public void testGetVerifiedEmailTrophy() {
    assertEquals(verifiedEmail.getIcon70(), "https://www.redditstatic.com/awards2/verified_email-70.png");
    assertNull(verifiedEmail.getGrantedAt());
    assertNull(verifiedEmail.getUrl());
    assertEquals(verifiedEmail.getIcon40(), "https://www.redditstatic.com/awards2/verified_email-40.png");
    assertEquals(verifiedEmail.getName(), "Verified Email");
    assertEquals(verifiedEmail.getAwardId(), "o");
    assertEquals(verifiedEmail.getId(), "1ridbv");
    assertNull(verifiedEmail.getDescription());
  }
}
