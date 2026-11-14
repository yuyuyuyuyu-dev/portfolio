config.files.push({
  pattern: "kotlin/composeResources/**",
  included: false,
  served: true,
  watched: false,
});

config.set({
  proxies: {
    "/composeResources/": "/base/kotlin/composeResources/",
  },
  client: {
    mocha: {
      timeout: 30000,
    },
  },
});
