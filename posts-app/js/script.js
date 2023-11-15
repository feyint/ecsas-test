document.addEventListener("DOMContentLoaded", function () {    
    loadPosts();        
});




function loadPage(page) {
    console.log("PAGINAS:", page);    
    const contentContainer = document.getElementById('content');

    // Agrega animación con anime.js
    anime({
        targets: contentContainer,
        translateX: 0, // -> '250px'
        rotate: 360 // -> '540deg'
    });

    if(page==='index.html') {
        window.location.href = "index.html";
    }

     axios.get(`pages/${page}`)
      .then(response => {
        switch (page) {           
            case "users.html":
                loadUsers();
            break;
        }

     setTimeout(() => {
        contentContainer.classList.remove('animate__fadeIn');
      }, 500); 

        document.getElementById('content').innerHTML = response.data;
      })
      .catch(error => {
        console.error('Error loading page', error);
      });
  }

  function loadUsers() {
    axios.get('https://jsonplaceholder.typicode.com/users')
      .then(response => {
        const users = response.data;
        const content = generateUsersHtml(users);
        document.getElementById('content').innerHTML = content;
      })
      .catch(error => {
        console.error('Error loading users', error);
      });
  }

  function generateUsersHtml(users) {
    let html = '<h2>Users</h2>';
    html += '<div class="row">';
    users.forEach(user => {
      html += `
        <div class="col-md-4 mb-4">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">${user.name}</h5>
              <p class="card-text">${user.email}</p>
              <p class="card-text">${user.phone}</p>
            </div>
          </div>
        </div>
      `;
    });
    html += '</div>';
    return html;
  }



  function getPosts(){
    return axios.get(`https://jsonplaceholder.typicode.com/posts`)
        .then(response => {
            const posts = response.data;          
            return posts; // Retorna los objetos de posts
        })
        .catch(error => {
            console.error('Error loading posts', error);            
            return []; // Retorna una array vacío en caso de error
        });
  }

  function getUsers(){
    return axios.get(`https://jsonplaceholder.typicode.com/users`)
    .then(response => {
        const users = response.data;          
        return users; // Retorna los objetos de posts
    })
    .catch(error => {
        console.error('Error loading posts', error);            
        return []; // Retorna una array vacío en caso de error
    });
  }



  function loadPosts() { 
    getPosts().then(posts => {        
        getUsers().then(users => {            
            let html = `
            <div id="postCarousel" class="carousel slide" data-bs-ride="carousel">
                    <button class="carousel-control-prev" type="button" data-bs-target="#postCarousel" data-bs-slide="prev" style="z-index: 10; left: -60px;">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                <div class="carousel-inner" >
            `;         
            i = 1;
            posts.forEach((post, index) => {  

                const user = users.find(user => user.id === post.userId);
                const activeClass = index === 0 ? 'active' : '';

                let img = `img_${i}.jpg`;
                let per = `person_${i}.jpg`;
                
                if(i>3){
                    img = `landing_1.png`;
                    per = `landing_1.png`;
                }

                


                html += `
                <div class="carousel-item ${activeClass}">
                <div class="card">
                    <img  id="img" src="assets/images/${img}" class="d-block w-100" alt="Imagen">
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${post.title}</h5>
                        <p class="card-text">${post.body}</p>


                        <div class="container">
                            <div class="row">
                                <!-- Primera columna -->
                                <div class="col">
                                    <img src="assets/images/${per}" class="d-block w-100" alt="Persona">    
                                </div>
                                
                                <div class="col">
                                    <p class="card-text">Usuario: ${user.name}</p>
                                    <p class="card-text">Email: ${user.email}</p>
                                    <p class="card-text">Ciudad: ${user.address.city}</p>                                
                                </div>
                            </div>
                            </div>                       

                    </div>
                </div>
            </div>
                `;

                i++;
            });

            html += `
            </div>            
            <button class="carousel-control-next" type="button" data-bs-target="#postCarousel" data-bs-slide="next" style="z-index: 10; right: -60px;" >
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        `;

        document.getElementById('content').innerHTML += html;

       /*  setTimeout(() => {
            const postCarousel = document.getElementById('postCarousel');
            new bootstrap.Carousel(postCarousel, { interval: false });
        }, 1000); */

        setTimeout(() => {
            const postCarousel = document.getElementById('postCarousel');
            const bootstrapCarousel = new bootstrap.Carousel(postCarousel, { interval: false });

            postCarousel.addEventListener('slid.bs.carousel', function () {
                const contentContainer = document.getElementById('content');
                anime({
                    targets: contentContainer,
                    translateX: 0, // -> '250px'
                    rotate: 360 // -> '540deg'
                });
            });
        }, 1000);

            
        });
    });
}

function fadeIn(element, currentOpacity, targetOpacity, duration) {
    const startTime = performance.now();

    function updateOpacity() {
        const elapsed = performance.now() - startTime;
        const progress = elapsed / duration;

        if (progress >= 1) {
            element.style.opacity = targetOpacity;
        } else {
            element.style.opacity = currentOpacity + progress * (targetOpacity - currentOpacity);
            requestAnimationFrame(updateOpacity);
        }
    }

    updateOpacity();
}