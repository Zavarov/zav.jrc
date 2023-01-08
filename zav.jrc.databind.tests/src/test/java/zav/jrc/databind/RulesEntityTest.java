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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Checks whether the attributes of a rules entity have been properly deserialized.
 */
public class RulesEntityTest extends AbstractTest {
  static RulesEntity rules;
  static List<RuleEntity> ruleList;
  static List<NextStepReasonEntity> siteRulesFlowList;

  /**
   * Instantiates the rules DTO and retrieves the individual rules.
   */
  @BeforeAll
  public static void setUpAll() {
    rules = read("Rules.json", RulesEntity.class);
    ruleList = rules.getRules();
    siteRulesFlowList = rules.getSiteRulesFlow();
  }

  @Test
  public void testGetSiteRules() {
    assertEquals(rules.getSiteRules().size(), 3);
    assertEquals(rules.getSiteRules().get(0), "Spam");
    assertEquals(rules.getSiteRules().get(1), "Personal and confidential information");
    assertEquals(rules.getSiteRules().get(2), "Threatening, harassing, or inciting violence");
  }

  @Test
  public void testGetRules() {
    RuleEntity rule;
    assertEquals(ruleList.size(), 4);

    rule = ruleList.get(0);
    assertEquals(rule.getViolationReason(), "Relevance");
    assertEquals(rule.getKind(), "link");
    assertEquals(rule.getDescription(), "Posts must directly relate to Reddit's API, API libraries, or Reddit's source code. Ideas for changes belong in /r/ideasfortheadmins; bug reports should be posted to /r/bugs; general Reddit questions should be made in /r/help; and requests for bots should be made to /r/requestabot.");
    assertEquals(rule.getShortName(), "Relevance");
    assertEquals(rule.getDescriptionHtml(), "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Posts must directly relate to Reddit&amp;#39;s API, API libraries, or Reddit&amp;#39;s source code. Ideas for changes belong in &lt;a href=\"/r/ideasfortheadmins\"&gt;/r/ideasfortheadmins&lt;/a&gt;; bug reports should be posted to &lt;a href=\"/r/bugs\"&gt;/r/bugs&lt;/a&gt;; general Reddit questions should be made in &lt;a href=\"/r/help\"&gt;/r/help&lt;/a&gt;; and requests for bots should be made to &lt;a href=\"/r/requestabot\"&gt;/r/requestabot&lt;/a&gt;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertEquals(rule.getCreatedUtc(), 1.465977513E9);
    assertEquals(rule.getPriority(), 0);

    rule = ruleList.get(1);
    assertEquals(rule.getViolationReason(), "Unproductive response");
    assertEquals(rule.getKind(), "comment");
    assertEquals(rule.getDescription(), "Comments should be productive and helpful, not condescending. If you can't help answer someone's question or direct them to an appropriate subreddit, you should not comment at all.");
    assertEquals(rule.getShortName(), "Unproductive response");
    assertEquals(rule.getDescriptionHtml(), "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Comments should be productive and helpful, not condescending. If you can&amp;#39;t help answer someone&amp;#39;s question or direct them to an appropriate subreddit, you should not comment at all.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertEquals(rule.getCreatedUtc(), 1.465977814E9);
    assertEquals(rule.getPriority(), 1);

    rule = ruleList.get(2);
    assertEquals(rule.getViolationReason(), "Language specific");
    assertEquals(rule.getKind(), "link");
    assertEquals(rule.getDescription(), "Posts should not ask questions that could otherwise be answered in a language-specific subreddit. For example, questions like, \"how do I install PRAW?\" are better asked in /r/learnpython, as the question can be generalized to, \"how do I install a package?\".");
    assertEquals(rule.getShortName(), "Language specific");
    assertEquals(rule.getDescriptionHtml(), "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;Posts should not ask questions that could otherwise be answered in a language-specific subreddit. For example, questions like, &amp;quot;how do I install PRAW?&amp;quot; are better asked in &lt;a href=\"/r/learnpython\"&gt;/r/learnpython&lt;/a&gt;, as the question can be generalized to, &amp;quot;how do I install a package?&amp;quot;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertEquals(rule.getCreatedUtc(), 1.514607739E9);
    assertEquals(rule.getPriority(), 2);

    rule = ruleList.get(3);
    assertEquals(rule.getViolationReason(), "Not a testing ground");
    assertEquals(rule.getKind(), "all");
    assertEquals(rule.getDescription(), "r/redditdev is not the right place to test your bots &amp; scripts. Please create your own private subreddit, or use /r/test.");
    assertEquals(rule.getShortName(), "Not a testing ground");
    assertEquals(rule.getDescriptionHtml(), "&lt;!-- SC_OFF --&gt;&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"/r/redditdev\"&gt;r/redditdev&lt;/a&gt; is not the right place to test your bots &amp;amp; scripts. Please create your own private subreddit, or use &lt;a href=\"/r/test\"&gt;/r/test&lt;/a&gt;.&lt;/p&gt;\n&lt;/div&gt;&lt;!-- SC_ON --&gt;");
    assertEquals(rule.getCreatedUtc(), 1.592349861E9);
    assertEquals(rule.getPriority(), 3);
  }

  @Test
  public void testGetSiteRulesFlow() {
    NextStepReasonEntity nextStepReason;
    assertEquals(siteRulesFlowList.size(), 4);

    nextStepReason = siteRulesFlowList.get(0);
    assertEquals(nextStepReason.getReasonTextToShow(), "This is spam");
    assertEquals(nextStepReason.getReasonText(), "This is spam");

    nextStepReason = siteRulesFlowList.get(1);
    assertEquals(nextStepReason.getReasonTextToShow(), "This is misinformation");
    assertEquals(nextStepReason.getReasonText(), "This is misinformation");

    nextStepReason = siteRulesFlowList.get(2);
    assertEquals(nextStepReason.getReasonTextToShow(), "This is abusive or harassing");
    assertTrue(nextStepReason.getReasonText().isEmpty());
    assertEquals(nextStepReason.getNextStepReasons().size(), 5);
    assertEquals(nextStepReason.getNextStepHeader(), "In what way?");

    nextStepReason = siteRulesFlowList.get(3);
    assertEquals(nextStepReason.getReasonTextToShow(), "Other issues");
    assertTrue(nextStepReason.getReasonText().isEmpty());
    assertEquals(nextStepReason.getNextStepReasons().size(), 8);
    assertEquals(nextStepReason.getNextStepHeader(), "What issue?");
  }
}
