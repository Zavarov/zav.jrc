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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RulesTest extends AbstractTest {
  static Rules rules;
  static List<Rule> ruleList;
  static List<NextStepReason> siteRulesFlowList;
  
  @BeforeAll
  public static void setUpAll() {
    rules = read("Rules.json", Rules.class);
    ruleList = rules.getRules();
    siteRulesFlowList = rules.getSiteRulesFlow();
  }
  
  @Test
  public void testGetSiteRules() {
    assertThat(rules.getSiteRules()).containsExactly("Spam", "Personal and confidential information", "Threatening, harassing, or inciting violence");
  }
  
  @Test
  public void testGetRules() {
    Rule rule;
    assertThat(ruleList).hasSize(4);
    
    rule = ruleList.get(0);
    assertThat(rule.getViolationReason()).isEqualTo("Relevance");
    assertThat(rule.getKind()).isEqualTo("link");
    assertThat(rule.getDescription()).isEqualTo("Posts must directly relate to Reddit's API, API libraries, or Reddit's source code. Ideas for changes belong in /r/ideasfortheadmins; bug reports should be posted to /r/bugs; general Reddit questions should be made in /r/help; and requests for bots should be made to /r/requestabot.");
    assertThat(rule.getShortName()).isEqualTo("Relevance");
    assertThat(rule.getDescriptionHtml()).isEqualTo("&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Posts must directly relate to Reddit&amp;#39;s API, API libraries, or Reddit&amp;#39;s source code. Ideas for changes belong in &lt;a href=\"/r/ideasfortheadmins\"&gt;/r/ideasfortheadmins&lt;/a&gt;; bug reports should be posted to &lt;a href=\"/r/bugs\"&gt;/r/bugs&lt;/a&gt;; general Reddit questions should be made in &lt;a href=\"/r/help\"&gt;/r/help&lt;/a&gt;; and requests for bots should be made to &lt;a href=\"/r/requestabot\"&gt;/r/requestabot&lt;/a&gt;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertThat(rule.getCreatedUtc()).isEqualTo(1.465977513E9);
    assertThat(rule.getPriority()).isEqualTo(0);
  
    rule = ruleList.get(1);
    assertThat(rule.getViolationReason()).isEqualTo("Unproductive response");
    assertThat(rule.getKind()).isEqualTo("comment");
    assertThat(rule.getDescription()).isEqualTo("Comments should be productive and helpful, not condescending. If you can't help answer someone's question or direct them to an appropriate subreddit, you should not comment at all.");
    assertThat(rule.getShortName()).isEqualTo("Unproductive response");
    assertThat(rule.getDescriptionHtml()).isEqualTo("&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Comments should be productive and helpful, not condescending. If you can&amp;#39;t help answer someone&amp;#39;s question or direct them to an appropriate subreddit, you should not comment at all.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertThat(rule.getCreatedUtc()).isEqualTo(1.465977814E9);
    assertThat(rule.getPriority()).isEqualTo(1);
  
    rule = ruleList.get(2);
    assertThat(rule.getViolationReason()).isEqualTo("Language specific");
    assertThat(rule.getKind()).isEqualTo("link");
    assertThat(rule.getDescription()).isEqualTo("Posts should not ask questions that could otherwise be answered in a language-specific subreddit. For example, questions like, \"how do I install PRAW?\" are better asked in /r/learnpython, as the question can be generalized to, \"how do I install a package?\".");
    assertThat(rule.getShortName()).isEqualTo("Language specific");
    assertThat(rule.getDescriptionHtml()).isEqualTo("&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Posts should not ask questions that could otherwise be answered in a language-specific subreddit. For example, questions like, &amp;quot;how do I install PRAW?&amp;quot; are better asked in &lt;a href=\"/r/learnpython\"&gt;/r/learnpython&lt;/a&gt;, as the question can be generalized to, &amp;quot;how do I install a package?&amp;quot;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertThat(rule.getCreatedUtc()).isEqualTo(1.514607739E9);
    assertThat(rule.getPriority()).isEqualTo(2);
  
    rule = ruleList.get(3);
    assertThat(rule.getViolationReason()).isEqualTo("Not a testing ground");
    assertThat(rule.getKind()).isEqualTo("all");
    assertThat(rule.getDescription()).isEqualTo("r/redditdev is not the right place to test your bots &amp; scripts. Please create your own private subreddit, or use /r/test.");
    assertThat(rule.getShortName()).isEqualTo("Not a testing ground");
    assertThat(rule.getDescriptionHtml()).isEqualTo("&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"/r/redditdev\"&gt;r/redditdev&lt;/a&gt; is not the right place to test your bots &amp;amp; scripts. Please create your own private subreddit, or use &lt;a href=\"/r/test\"&gt;/r/test&lt;/a&gt;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertThat(rule.getCreatedUtc()).isEqualTo(1.592349861E9);
    assertThat(rule.getPriority()).isEqualTo(3);
  }
  
  @Test
  public void testGetSiteRulesFlow() {
    NextStepReason nextStepReason;
    assertThat(siteRulesFlowList).hasSize(4);
    
    nextStepReason = siteRulesFlowList.get(0);
    assertThat(nextStepReason.getReasonTextToShow()).isEqualTo("This is spam");
    assertThat(nextStepReason.getReasonText()).isEqualTo("This is spam");
  
    nextStepReason = siteRulesFlowList.get(1);
    assertThat(nextStepReason.getReasonTextToShow()).isEqualTo("This is misinformation");
    assertThat(nextStepReason.getReasonText()).isEqualTo("This is misinformation");
  
    nextStepReason = siteRulesFlowList.get(2);
    assertThat(nextStepReason.getReasonTextToShow()).isEqualTo("This is abusive or harassing");
    assertThat(nextStepReason.getReasonText()).isEmpty();
    assertThat(nextStepReason.getNextStepReasons()).hasSize(5);
    assertThat(nextStepReason.getNextStepHeader()).isEqualTo("In what way?");
  
    nextStepReason = siteRulesFlowList.get(3);
    assertThat(nextStepReason.getReasonTextToShow()).isEqualTo("Other issues");
    assertThat(nextStepReason.getReasonText()).isEmpty();
    assertThat(nextStepReason.getNextStepReasons()).hasSize(8);
    assertThat(nextStepReason.getNextStepHeader()).isEqualTo("What issue?");
  }
}
