@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page
<!-- с передачей данных в модель page = page, -->
@template.layout.page(
page = page,
content = @`
    @for (var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
            <p></p>
        </div>
    @endfor
    <h6><a href="${NamedRoutes.buildUserPath()}">Add new user</a></h6>
`)
