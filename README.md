<!--
*** Thanks for checking out this README Template. If you have a suggestion that would
*** make this better, please fork the repo and create a pull request or simply open
*** an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
***
***
***
*** To avoid retyping too much info. Do a search and replace for the following:
*** github_username, repo, twitter_handle, email
-->





<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/birdeveloper/rapi">
    <img src="rapi_logo.png" alt="Rapi Logo" >
  </a>

  <h3 align="center">Rapi</h3>

  <p align="center">
    We have made retrofit more complex!
    <br />
    <a href="https://github.com/birdeveloper/rapi"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/birdeveloper/rapi">View Demo</a>
    ·
    <a href="https://github.com/birdeveloper/rapi/issues">Report Bug</a>
    ·
    <a href="https://github.com/birdeveloper/rapi/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
  * [Setup](#setup)
  * [GET Request](#get-request)
  * [POST Request](#post-request)
  * [POST RAW Request](#post-raw-request)
  * [PUT Request](#put-request)
  * [DELETE Request](#delete-request)
  * [DOWNLOAD Request](#download-request)
  * [UPLOAD Request](#upload-request)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)



<!-- ABOUT THE PROJECT -->
## About The Project
<a href="https://github.com/birdeveloper/rapi">
    <img src="Rapi_Explore.png" alt="Rapi Explore Github" >
  </a>


Rapi offers you a lot of innovations by shortening the steps that retrofit should take in a **very short** and concise way. It **organizes the network requests in the background** and easily adapts to the **MVVM** structure.


### Built With

* [okhttp3]()
* [retrofit2]()
* [kotlin]()



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.  (already installed in the library)
* retrofit
```sh
implementation 'com.squareup.retrofit2:retrofit:2.6.3'
```

### Installation
 To get a Git project into your build:
**Step 1.** Add the JitPack repository to your build file. Add it in your **root build.gradle** at the end of repositories:
```sh
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }
```
**Step 2.** Add the dependency
```sh
dependencies {
          implementation 'com.github.birdeveloper:Rapi:1.0.0'
  }
```



<!-- USAGE EXAMPLES -->
## Usage

### Setup

```sh
        val headers: WeakHashMap<String, String> = WeakHashMap()
        headers["Authorization"] = "Bearer vF9dft4qmTFDfd27asWEgf"
        Rapi.init(this,Constants.BASE_URL, headers, 120)
        // context, base url, headers or null, timeout
```

### GET Request

```sh
          Rapi.buider()
            .request("https://reqres.in/api/users/")
            .params("page",2)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .get()
```

### POST Request

```sh
          Rapi.buider()
            .request("https://reqres.in/api/users/")
            .params("name","Görkem")
            .params("job","Android Developer")
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .post()
```

### POST RAW Request

```sh
        val jsonStr = "{\"name\":\"Görkem\",\"job\":\"Android Developer\"}"
        Rapi.buider()
            .request(URL_USERS)
            .raw(jsonStr)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .post()
```

### PUT Request

```sh
        Rapi.buider()
            .request(Constants.URL_USER_ID)
            .params("name","Resul")
            .params("job","Software Developer")
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .put()
```

### DELETE Request

```sh
        Rapi.buider()
            .request(Constants.URL_USER_ID)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d("onSuccess", response)
                }
            })
            .failure(object :IFailure{
                override fun onFailure() {
                    Log.d("onFailure", "yes")
                }
            })
            .error(object : IError{
                override fun onError(code: Int, msg: String) {
                    Log.d("onError", "code: $code  -----  msg: $msg")
                }

            })
            .build()
            .delete()
```

### DOWNLOAD Request

```sh
            /*
            IMPORTANT!!! Do not forget to write and read permission before discarding this request !!
            <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
            There are codes related to the permissions in the sample file.
             */
        Rapi.buider()
                .request(Constants.URL_DOWNLOAD)
                .dir(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
                .extension("doc")
                .name("example.doc")
                .success(object : ISuccess {
                    override fun onSuccess(response: String) {
                        Log.d("onSuccess", response)
                    }
                })
                .failure(object :IFailure{
                    override fun onFailure() {
                        Log.d("onFailure", "yes")
                    }
                })
                .error(object : IError{
                    override fun onError(code: Int, msg: String) {
                        Log.d("onError", "code: $code  -----  msg: $msg")
                    }
                })
                .build()
                .download()
```

### UPLOAD Request

```sh
            /*
            IMPORTANT!!! Do not forget to write and read permission before discarding this request !!
            <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
            There are codes related to the permissions in the sample file.
             */
        Rapi.buider()
                .request(Constants.URL_UPLOAD)
                .file(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/example.jpeg"))
                .onRequest(object : IRequest{
                    override fun onRequestStart() {
                        Log.d("onRequestStart", "run")
                    }

                    override fun onRequestEnd() {
                        Log.d("onRequestEnd", "finish")
                    }

                })
                .success(object : ISuccess {
                    override fun onSuccess(response: String) {
                        Log.d("onSuccess", response)
                    }
                })
                .failure(object :IFailure{
                    override fun onFailure() {
                        Log.d("onFailure", "yes")
                    }
                })
                .error(object : IError{
                    override fun onError(code: Int, msg: String) {
                        Log.d("onError", "code: $code  -----  msg: $msg")
                    }
                })
                .build()
                .upload()
```

<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/birdeveloper/rapi/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b birdeveloper/Rapi`)
3. Commit your Changes (`git commit -m 'Add some Rapi'`)
4. Push to the Branch (`git push origin birdeveloper/Rapi`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Your Name - [@gorkemkara](https://www.linkedin.com/in/gorkemkara/) - [email](mailto:birdeveloper.com@gmail.com)

Project Link: [https://github.com/birdeveloper/rapi](https://github.com/birdeveloper/rapi)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* []()





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/birdeveloper/Rapi/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=flat-square
[forks-url]: https://github.com/birdeveloper/Rapi/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=flat-square
[stars-url]: https://github.com/birdeveloper/Rapi/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=flat-square
[issues-url]: https://github.com/birdeveloper/Rapi/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/birdeveloper/Rapi/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/gorkemkara/
[product-screenshot]: images/screenshot.png
