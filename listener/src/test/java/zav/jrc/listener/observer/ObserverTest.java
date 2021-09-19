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

package zav.jrc.listener.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zav.jrc.FailedRequestException;
import zav.jrc.listener.AbstractTest;
import zav.jrc.listener.SubredditListener;
import zav.jrc.listener.guice.ObserverMock;
import zav.jrc.listener.guice.SubredditListenerMock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ObserverTest extends AbstractTest {
  private static Observer<SubredditListener> observer;
  private List<SubredditListener> responses;
  private SubredditListener foo;
  private SubredditListener bar;

  @BeforeEach
  public void setUp(){
    observer = new ObserverMock();
    responses = new ArrayList<>();
    foo = new SubredditListenerMock(responses::add);
    bar = new SubredditListenerMock(responses::add);
  }
  
  @Test
  public void testAddListener() {
    assertThat(observer.size()).isEqualTo(0);
    // Add twice, count as one
    assertThat(observer.addListener(foo)).isTrue();
    assertThat(observer.addListener(foo)).isFalse();
    assertThat(observer.size()).isEqualTo(1);
  }
  
  @Test
  public void testRemoveListener() {
    assertThat(observer.size()).isEqualTo(0);
    assertThat(observer.addListener(foo)).isTrue();
    assertThat(observer.size()).isEqualTo(1);
    assertThat(observer.addListener(bar)).isTrue();
    assertThat(observer.size()).isEqualTo(2);
  
    assertThat(observer.removeListener(bar)).isTrue();
    assertThat(observer.size()).isEqualTo(1);
    assertThat(observer.removeListener(foo)).isTrue();
    assertThat(observer.size()).isEqualTo(0);
    assertThat(observer.removeListener(bar)).isFalse();
    assertThat(observer.removeListener(foo)).isFalse();
  }
  
  @Test
  public void testNotifyListener() throws FailedRequestException {
    assertThat(responses).hasSize(0);
    observer.addListener(foo);
    observer.addListener(bar);
    observer.notifyListener(bar);
    assertThat(responses).containsExactly(bar);
  }
  
  @Test
  public void testNotifyAllListeners() throws FailedRequestException {
    assertThat(responses).hasSize(0);
    observer.addListener(foo);
    observer.addListener(bar);
    observer.notifyAllListeners();
    assertThat(responses).containsExactlyInAnyOrder(foo, bar);
  }
  
  @Test
  public void testSize() {
    assertThat(observer.size()).isEqualTo(0);
    observer.addListener(foo);
    assertThat(observer.size()).isEqualTo(1);
    observer.addListener(bar);
    assertThat(observer.size()).isEqualTo(2);
  }
}
