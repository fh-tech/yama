$(document).ready(function () {
    initializeStudios()
});


async function initializeStudios() {
    removeAllChildren('studioAccordion');
    let container = $('#studioAccordion');
    let studios = await fetchStudios();
    studios = await studios.json();
    studios.forEach(
        studio => {
            container.append(buildStudioElement(studio))
        }
    )
}

function buildStudioElement(studio) {
    return `<div class="card">
                    <div class="card-header" id="heading${studio.id}">
                        <h2 class="mb-0">
                            <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${studio.id}" aria-expanded="false" aria-controls="collapse${studio.id}">
                                <span>${studio.name}</span>
                            </button>
                        </h2>
                    </div>
                    <div id="collapse${studio.id}" class="collapse" aria-labelledby="heading${studio.id}" data-parent="#studioAccordion">
                        <div class="card-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-6">
                                        <span>CountryCode: ${studio.countryCode}</span>
                                    </div>
                                    <div class="col-6">
                                        <span>Postcode: ${studio.postCode}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`
}

function fetchStudios() {
    let options = {
        headers: {
            "Accept": "application/json",
            "Authorization": "Basic " + btoa("reader:123")
        }
    };
    return fetch(`studio`, options);
}

function removeAllChildren(elementID) {
    let myNode = document.getElementById(elementID);
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }
}