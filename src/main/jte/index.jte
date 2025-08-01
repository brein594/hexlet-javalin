@import org.example.hexlet.dto.MainPage
@param MainPage page

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
        <h1 class="text-body-emphasis">Привет, Хекслет!</h1>
        @if(!page.isVisited())
        Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
        <p>Javalin + jte</p>

        <a href="/users">Users</a>
        <a href="/courses">Courses</a>
    </main>
</div>
</body>
</html>
