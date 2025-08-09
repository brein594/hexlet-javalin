@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.BasePage
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

<html lang="en">
<head>
    <title>Hexlet Courses</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<h1>${page.getHeader()}</h1>

<form action="/courses" method="get">
    <input type="search" name="term" value="${page.getTerm()}" />
    <input type="submit" value="Search" />
</form>
@if(page.getFlash() != null)
<div class="alert alert-success" role="alert">
 <p class="alert-heading">${page.getFlash()}</p>
</div>
@endif

@if(page.getCourses().isEmpty())
    <p>List course is empty</p>
@else
    @for(var course : page.getCourses())
        <div>
            <h2><a href="${NamedRoutes.coursePath(course.getId())}">${course.getName()}</a></h2>
            <p>${course.getDescription()}</p>
        </div>
    @endfor
@endif
<p><a href="${NamedRoutes.buildCoursePath()}">Add course</a></p>
<p><a href="${NamedRoutes.coursePath()}">courses</a></p>
<p><a href="/">Home</a></p>
</body>
</html>

