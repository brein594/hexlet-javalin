@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`

    <h1>Firstname ${page.getUser().getFirstName()} Lastname ${page.getUser().getLastName()}</h1>
    <p>Number in BD ${page.getUser().getId()}</p>
    <!-- <p><a href="${NamedRoutes.destroySessionsPath(page.getUser().getId())}">Delete</a></p> -->
    <p><a href="/">Home</a></p>
`)
