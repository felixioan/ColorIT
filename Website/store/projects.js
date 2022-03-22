var windowHidden = "visible";

export const state = () => ({
    list: [],
    team: [],
});

export const mutations = {
    setProjects(state, arr) {
        state.list = arr;
    },
    setTeam(state, arr) {
        state.team = arr;
    },
}

export const getters = {
    get(state) {
        return state.list;
    },
    getTeam(state) {
        return state.team;
    },
    getById(state) {
        return (id) => {
            for(const item of state.list) {
                if(item.id===id) {
                    return item;
                }
            }
        }
    },
}

export const actions = {
    async getProjectsData({commit, getters}) {
        await new Promise((resolve, reject) => {
            var dataScript = document.createElement('script');
            dataScript.onload = ()=> {
                if(window.projectsData) {
                    if(window.projectsData.projects && Array.isArray(window.projectsData.projects)) {
                        commit('setProjects', window.projectsData.projects);
                    }
                    if(window.projectsData.team && Array.isArray(window.projectsData.team)) {
                        commit('setTeam', window.projectsData.team);
                    }
                }
                resolve();
            }
            dataScript.setAttribute('src',process.env.NODE_ENV==='development'?'/data.js':'../data.js');
            document.head.appendChild(dataScript);
        });
        return getters['get'];
    },
    async initReceiveInterval({dispatch}) {
        dispatch('setWindowViwers');
        setInterval(() => {
            if(windowHidden==="visible") {
                dispatch('getProjectsData');
            }
        }, 3000);
    },
    async setWindowViwers({dispatch}) {
        var hidden = "hidden";

        // Standards:
        if (hidden in document) document.addEventListener("visibilitychange", onchange);
        else if ((hidden = "mozHidden") in document) document.addEventListener("mozvisibilitychange", onchange);
        else if ((hidden = "webkitHidden") in document) document.addEventListener("webkitvisibilitychange", onchange);
        else if ((hidden = "msHidden") in document) document.addEventListener("msvisibilitychange", onchange);
        // IE 9 and lower:
        else if ("onfocusin" in document) document.onfocusin = document.onfocusout = onchange;
        // All others:
        else window.onpageshow = window.onpagehide = window.onfocus = window.onblur = onchange;

        function onchange (evt) {
            var v = "visible", h = "hidden",
            evtMap = {
                focus:v, focusin:v, pageshow:v, blur:h, focusout:h, pagehide:h
            }

            evt = evt || window.event;
            if (evt.type in evtMap) windowHidden = evtMap[evt.type];
            else windowHidden = this[hidden] ? "hidden" : "visible";
            if(windowHidden==="visible") {
                dispatch('getProjectsData');
            }
        }

        // set the initial state (but only if browser supports the Page Visibility API)
        if( document[hidden] !== undefined ) onchange({type: document[hidden] ? "blur" : "focus"});
    },
}