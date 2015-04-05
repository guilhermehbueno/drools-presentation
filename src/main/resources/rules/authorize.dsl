[when][]There is a Person older than "{age}"=$user: User(age>="{age}")
[then][]set your authorize flag to "{flag}"=$user.setAuthorized({flag});
[then][]Print a welcome message "{message}"= System.out.println(buildMessageFor($user)+" {message}");