# Reddit Clone

Reddit is a social platform where users submit their posts and other users express their views by commenting on the post. Users can also upvote and downvote based on if they like it. If a post gets lots of upvotes it will be shown on the front page so the people can see it. If it gets downvotes it quickly falls and disappears from most people’s views.

## Technology stack

-   Spring boot web
-   Spring MVC as MVC framework
-   Thymeleaf
-   Spring security + JWT
-   PostgreSQL
-   Spring Data JPA for Database Connectivity
-   HTML, CSS and JS

## Use Cases:

### Non logged In User

-   A non logged in user can read a list of community blog posts that has a title, author, published DateTime and comment, upvote, and downvote count.
-   A non logged in user can read the full blog post.
-   A non logged in user can navigate to different communities, user can also read all the post related to that community.
-   A non logged in user can read community members count and upvote and downvote count.
-   A non logged in user can search community, post title, content, and author of a post.
-   A post that has the most upvotes will be shown first on the page.

### Logged In User

-   Users can create a post in any community with title, content, author name.
-   Users can update and delete their own posts.
-   Users having the role of **admin** can update and delete any post and comment.