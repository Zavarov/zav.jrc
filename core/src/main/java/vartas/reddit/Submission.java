/*
 * Copyright (c) 2020 Zavarov
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

import vartas.reddit.$visitor.RedditVisitor;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Submission extends SubmissionTOP{
    @Nonnull
    private static final String QUALIFIED_PERMALINK = "https://www.reddit.com/comments/%s/-/";

    @Nonnull
    private static final String SHORT_LINK = "https://redd.it/%s";

    @Nonnull
    @Override
    public String getQualifiedPermaLink(){
        return String.format(QUALIFIED_PERMALINK, getId());
    }

    @Nonnull
    @Override
    public String getShortLink(){
        return String.format(SHORT_LINK, getId());
    }

    @Nonnull
    @Override
    public String getQualifiedTitle(){
        StringBuilder titleBuilder = new StringBuilder();

        ifPresentLinkFlairText(flair  -> titleBuilder.append("[").append(flair).append("] "));
        titleBuilder.append(getTitle());
        if(getSpoiler()) titleBuilder.append(" [Spoiler]");
        if(getNsfw()) titleBuilder.append(" [NSFW]");

        return titleBuilder.toString();
    }

    @Nonnull
    @Override
    public List<Comment> getAllComments(){
        List<Comment> comments = new ArrayList<>();

        RedditVisitor commentVisitor = new RedditVisitor(){
            @Override
            public void visit(@Nonnull Comment comment){
                comments.add(comment);
            }
        };

        accept(commentVisitor);

        return comments;
    }

    @Override
    public Submission getRealThis() {
        return this;
    }
}
