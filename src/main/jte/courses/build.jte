@import org.example.hexlet.NamedRoutes


@template.layout.page(
content = @`
    <h1>Форма добавления курса</h1>

    <form action="${NamedRoutes.coursePath()}" method="post">
        <div>
            <label>
                Курс
                <input type="text" required name="name" />
            </label>
        </div>
        <div>
            <label>
             Описание
                <input type="text" name="description" />
            </label>
        </div>
    <input type="submit" value="Добавить курс" />
    </form>

`)