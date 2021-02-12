<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #f3caca;">
    <a class="navbar-brand" href="/">Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/admin">Admin pannel</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/cart">Cart</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Lang
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?lang=en">Eng</a>
                    <a class="dropdown-item" href="?lang=ua">Ua</a>
                </div>
            </li>
            <li class="nav-item active">
                <div class="dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="drop" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> My account
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/user/">My account</a></li>
                        <li><a class="dropdown-item" href="/logout">Log out</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</nav>
