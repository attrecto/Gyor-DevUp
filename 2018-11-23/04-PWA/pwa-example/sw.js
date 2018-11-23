const CACHE = 'cache-v1'

const PRECACHE_URLS = [
	'./',
	'lorem.html',
	'devup-192.png',
	'devup-512.png'
]

function onInstall(evt) {
	evt.waitUntil(async function() {
		console.log('SW INSTALL')
		// Fill cache
		let cache = await caches.open(CACHE)
		await cache.addAll(PRECACHE_URLS)
		// WARNING: This does not wait for clients to exit, service worker is
		// activated immediately. This can be dangerious!
		self.skipWaiting()
	}())
}

function onActivate(evt) {
	evt.waitUntil(async function() {
		console.log('SW ACTIVATE')
		// Drop old caches
		let cacheList = (await caches.keys()).filter(name => name !== CACHE)
		await Promise.all(cacheList.map(name => caches.delete(name)))
		// WARNING: Does not wait for clients to exit, service worker is
		// claiming all running clients immediately. This can be dangerious!
		await self.clients.claim()
	}())
}

function onFetch(evt) {
	// We handle only GET requests for our own origin. Any other request is
	// handled by the default handler
	if (evt.request.method !== 'GET' || !evt.request.url.startsWith(self.location.origin)) {
		return
	}

	evt.respondWith(async function() {
		// First search in the cache
		let res = await caches.match(evt.request)
		if (res) {
			console.log('SW CACHE', evt.request.url)
			return res
		}
		let cache = await caches.open(CACHE)
		try {
			// If not in cache, then fetch(), then store in cache
			console.log('SW FETCH', evt.request.url)
			res = await fetch(evt.request)
			await cache.put(evt.request, res.clone())
			return res
		} catch(err) {
			console.log('SW FETCH ERROR')
			return null
		}
	}())
}

self.addEventListener('install', onInstall)
self.addEventListener('activate', onActivate)
self.addEventListener('fetch', onFetch)

// vim: ts=4
