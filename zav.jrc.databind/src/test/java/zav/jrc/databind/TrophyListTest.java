/*
 * Copyright (c) 2021 Zavarov.
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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a trophy list DTO have been properly deserialized.
 */
public class TrophyListTest extends AbstractTest {
  static TrophyList trophyList;
  static Award threeYearClub;
  static Award verifiedEmail;
  
  /**
   * Instantiates the trophy list DTO and retrieves the individual awards.
   */
  @BeforeAll
  public static void setUpAll() {
    trophyList = read("TrophyList.json", TrophyList.class);
    threeYearClub = read(trophyList.getTrophies().get(0).getData(), Award.class);
    verifiedEmail = read(trophyList.getTrophies().get(1).getData(), Award.class);
  }
  
  @Test
  public void testGetTrophies() {
    assertThat(trophyList.getTrophies()).hasSize(2);
  }
  
  @Test
  public void testGetThreeYearClubTrophy() {
    assertThat(threeYearClub.getIcon70()).isEqualTo("https://www.redditstatic.com/awards2/3_year_club-70.png");
    assertThat(threeYearClub.getGrantedAt()).isEqualTo(1592849930);
    assertThat(threeYearClub.getUrl()).isNull();
    assertThat(threeYearClub.getIcon40()).isEqualTo("https://www.redditstatic.com/awards2/3_year_club-40.png");
    assertThat(threeYearClub.getName()).isEqualTo("Three-Year Club");
    assertThat(threeYearClub.getAwardId()).isNull();
    assertThat(threeYearClub.getId()).isNull();
    assertThat(threeYearClub.getDescription()).isNull();
  }
  
  @Test
  public void testGetVerifiedEmailTrophy() {
    assertThat(verifiedEmail.getIcon70()).isEqualTo("https://www.redditstatic.com/awards2/verified_email-70.png");
    assertThat(verifiedEmail.getGrantedAt()).isNull();
    assertThat(verifiedEmail.getUrl()).isNull();
    assertThat(verifiedEmail.getIcon40()).isEqualTo("https://www.redditstatic.com/awards2/verified_email-40.png");
    assertThat(verifiedEmail.getName()).isEqualTo("Verified Email");
    assertThat(verifiedEmail.getAwardId()).isEqualTo("o");
    assertThat(verifiedEmail.getId()).isEqualTo("1ridbv");
    assertThat(verifiedEmail.getDescription()).isNull();
  }
}
