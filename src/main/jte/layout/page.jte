@import gg.jte.Content
@import org.example.hexlet.dto.BasePage

@param Content content
@param BasePage page = null

<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Hexlet Javalin Example</title>
</head>
<body>
    <p>
        <a href="/users">Users</a>
        <a href="/courses">Courses</a>
    </p>
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-success" role="alert">
            <p class="alert-heading">${page.getFlash()}</p>
        </div>
    @endif
    ${content}
</body>
</html>


