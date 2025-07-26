@template.layout.page(
content = @`
    <form action="/users" method="post">
        <div>
            <label>
                Имя
                <input type="text" name="firsName" />
            </label>
        </div>
        <div>
            <label>
                Фамилия
                <input type="text" required name="lastName" />
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" required name="password" />
            </label>
        </div>
        <div>
            <label>
                Подтверждение пароля
                <input type="password" required name="passwordConfirmation" />
            </label>
        </div>
        <input type="submit" value="Зарегистрировать" />
    </form>
`)