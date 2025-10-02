// ===== CONFIG =====
const OWM_API_KEY = '1e1c6478562ebde5284a7cc94ebbbffb';
const BACKEND_URL = 'http://localhost:8080'; // your Spring Boot backend URL

// ===== Local storage keys =====
const STORAGE = { LIKES: 'mo_likes_v1', PLAYLIST: 'mo_playlist_v1' };

// ===== App state =====
let songs = [];
let likes = new Set();
let playlist = [];
let currentMood = '';
let currentWeatherTag = '';

// ===== Dom refs =====
const songsEl = document.getElementById('songs');
const playlistEl = document.getElementById('playlist');
const player = document.getElementById('player');
const currentMoodEl = document.getElementById('currentMood');
const currentWeatherEl = document.getElementById('currentWeather');
const weatherHint = document.getElementById('weatherHint');

// ===== Load & Save state =====
function loadState(){
  const savedLikes = JSON.parse(localStorage.getItem(STORAGE.LIKES) || '[]');
  likes = new Set(savedLikes);
  playlist = JSON.parse(localStorage.getItem(STORAGE.PLAYLIST) || '[]');
}
function saveLikes(){ localStorage.setItem(STORAGE.LIKES, JSON.stringify(Array.from(likes))); }
function savePlaylist(){ localStorage.setItem(STORAGE.PLAYLIST, JSON.stringify(playlist)); }

// ===== Fetch songs from backend =====
async function fetchSongs(){
  try {
    const res = await fetch(`${BACKEND_URL}/songs`);
    if(!res.ok) throw new Error('Failed to fetch songs');
    songs = await res.json();
    renderSongs();
    renderPlaylist();
  } catch(e){
    console.error(e);
    songs = [];
  }
}

// ===== Render functions =====
function renderSongs(list = songs){
  songsEl.innerHTML = '';
  if(!list.length){ songsEl.innerHTML = '<div class="muted">No songs found.</div>'; return; }
  list.forEach(s=>{
    const div = document.createElement('div');
    div.className = 'song';
    div.innerHTML = `
      <div style="width:56px; height:56px; border-radius:10px; background:linear-gradient(180deg, rgba(255,255,255,0.02), rgba(255,255,255,0.01)); display:flex;align-items:center;justify-content:center;font-weight:700">
        🎵
      </div>
      <div class="meta">
        <div class="title">${escapeHtml(s.title)}</div>
        <div class="sub">${escapeHtml(s.artist)}</div>
        <div class="tags">${(s.genres||[]).map(g=>`<span class="tag">${g}</span>`).join('')}${(s.moods||[]).map(m=>`<span class="tag">${m}</span>`).join('')}</div>
      </div>
      <div class="actions">
        <button class="like" data-id="${s.id}">${likes.has(s.id)?'❤️':'🤍'}</button>
        <button class="small" onclick="playSong('${s.url}')">▶️ Play</button>
      </div>
    `;
    songsEl.appendChild(div);
  });

  document.querySelectorAll('.like').forEach(btn=>{
    btn.onclick = ()=>{ toggleLike(btn.dataset.id); btn.textContent = likes.has(btn.dataset.id)?'❤️':'🤍'; };
  });
}

function renderPlaylist(){
  playlistEl.innerHTML = playlist.map(p=>`<div>${escapeHtml(p.title)} — ${escapeHtml(p.artist)}</div>`).join('');
}

// ===== Like / playlist =====
function toggleLike(id){
  if(likes.has(id)) likes.delete(id);
  else likes.add(id);
  saveLikes();
}

// ===== Play song =====
function playSong(url){
  player.src = url;
  player.play();
}

// ===== Utils =====
function escapeHtml(text){ return text.replace(/[&<>"']/g, m=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[m])); }

// ===== Init =====
loadState();
fetchSongs();
