@import gg.jte.Content
@import org.example.hexlet.dto.BasePage

@param Content content
@param BasePage page = null

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Hexlet Javalin Example</title>
</head>
<body>
    <p>
        <a href="/users">Users</a>
        <a href="/courses">Courses</a>
    </p>
    @if(page != null && page.getFlash() != null)
        <p>${page.getFlash()}</p>
    @endif
    ${content}
</body>
</html>


