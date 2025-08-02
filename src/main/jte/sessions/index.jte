@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPageSession
@param MainPageSession page

@template.layout.page(
content = @`
    <main>
        <h1>Привет, Хекслет!</h1>
        @if(page.getCurrentUser() != null)
            Добро пожаловать, ${page.getCurrentUser()}.
            <p>Чтобы разлогиниться, удалите куку JSESSIONID из браузера</p>
            <p><a href="${NamedRoutes.destroySessionsPath()}">сброс</a></p>
        @endif
        <p><a href="${NamedRoutes.buildSessionsPath()}">ADD user</a></p>
    </main>
`)