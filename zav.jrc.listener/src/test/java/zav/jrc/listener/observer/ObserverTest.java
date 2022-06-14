/*
 * Copyright (c) 2022 Zavarov.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import zav.jrc.client.Client;
import zav.jrc.client.FailedRequestException;
import zav.jrc.databind.LinkEntity;
import zav.jrc.listener.SubredditListener;
import zav.jrc.listener.requester.LinkRequester;

/**
 * Checks whether listeners that have been added to an observer are properly notified.
 */
@ExtendWith(MockitoExtension.class)
public class ObserverTest {
  @Mock Client client;
  @Mock SubredditListener foo;
  @Mock SubredditListener bar;
  Observer<SubredditListener> observer;
  LinkRequester requester;
  
  /**
   * Creates mocks of the listeners {@link #foo} and {@link #bar}, as well as the observer
   * {@link #observer}.
   */
  @BeforeEach
  public void setUp() {
    try (MockedConstruction<LinkRequester> mocked = mockConstruction(LinkRequester.class)) {
      observer = new SubredditObserver(client, "subreddit");
      requester = mocked.constructed().get(0);
    }
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
    when(requester.next()).thenReturn(List.of(mock(LinkEntity.class)));
    
    observer.addListener(foo);
    observer.addListener(bar);
    observer.notifyListener(foo);
    verify(foo).notify(any());
    verify(bar, times(0)).notify(any());
  }
  
  @Test
  public void testNotifyAllListeners() throws Exception {
    when(requester.next()).thenReturn(List.of(mock(LinkEntity.class)));
    
    observer.addListener(foo);
    observer.addListener(bar);
    observer.notifyAllListeners();
    verify(foo).notify(any());
    verify(bar).notify(any());
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
