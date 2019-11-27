/*
 * Copyright (c) 2019 Zavarov
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

package vartas.reddit;

import vartas.reddit.visitor.SubredditSnowflakeVisitor;

/**
 * A snowflake inside a subreddit.<br>
 * This can be a {@link Submission} or a {@link Comment}, to name two.
 */
public interface SubredditSnowflake extends RedditSnowflake{
    /**
     * The URL body that will link to the subreddit, given the name.
     */
    String SUBREDDIT_URL = "https://www.reddit.com/r/%s";
    /**
     * @return The author of the snowflake.
     */
    String getAuthor();
    /**
     * @return The id of the snowflake.
     */
    String getId();
    /**
     * @return The upvotes minus the downvotes.
     */
    int getScore();
    /**
     * @return The name of the subreddit, the snowflake is in.
     */
    String getSubreddit();

    /**
     * @return The permalink to the users' page.
     */
    @Override
    default String getPermalink(){
        return String.format(SUBREDDIT_URL, getSubreddit());
    }

    /**
     * This method is part of the visitor pattern and used to visit the individual snowflakes
     * without having to rely on reflection.
     * @param visitor The visitor for this snowflake.
     */
    void accept(SubredditSnowflakeVisitor visitor);
}
