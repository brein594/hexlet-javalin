
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<html lang="en">
<head>
    <title>Hexlet Courses</title>
</head>
<body>
<h1>${page.getHeader()}</h1>

<form action="/courses" method="get">
    <input type="search" name="term" value="${page.getTerm()}" />
    <input type="submit" value="Search" />
</form>

@if(page.getCourses().isEmpty())
    <p>List course is empty</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="/courses/${Integer.toString(course.getId())}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
<p><a href="/courses">courses</a></p>
<p><a href="/">Home</a></p>
</body>
</html>
